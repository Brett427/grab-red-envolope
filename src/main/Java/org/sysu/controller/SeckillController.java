package org.sysu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.sysu.dto.Exposer;
import org.sysu.dto.SeckillExecution;
import org.sysu.dto.SeckillResult;
import org.sysu.enums.killStateEnum;
import org.sysu.exception.RepeatKillException;
import org.sysu.exception.SeckillCloseException;
import org.sysu.pojo.Seckill;
import org.sysu.service.SeckillService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/kill") //url:/模块/资源/{id}/
public class SeckillController {



    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value ="/list",method = RequestMethod.GET)
    public String list(Model model)
    {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";
    }
    @RequestMapping(value = "/{seckillid}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillid") Long id, Model model)
    {
        if(id==null)
        {
            return "redirect:/kill/list";
        }
        Seckill seckill = seckillService.getByid(id);
        if(seckill == null)
        {
            return "forward:/kill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }
    @RequestMapping(value = "/{seckillid}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> export(Long id)
    {
        SeckillResult<Exposer> result;
        try{
            Exposer exposer = seckillService.exportSeckillUrl(id);
            result =new SeckillResult<Exposer>(true,exposer);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            result =new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillid}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> ececute(@PathVariable("seckillid") Long seckillid,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killphone",required = false) Long phone)
    {
        if(phone==null)
        {
            return new SeckillResult<SeckillExecution>(false,"not regis");
        }
        SeckillResult<SeckillExecution> result;
        try
        {
            SeckillExecution execution =seckillService.executeSeckill(seckillid,phone,md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
        catch(RepeatKillException e1)
        {
            SeckillExecution seckillExecution = new SeckillExecution(seckillid,killStateEnum.REPEATKILL);
            return new SeckillResult<SeckillExecution>(false,seckillExecution);
        }
        catch(SeckillCloseException e2)
        {
            SeckillExecution seckillExecution = new SeckillExecution(seckillid,killStateEnum.END);
            return new SeckillResult<SeckillExecution>(false,seckillExecution);
        }
        catch(Exception e3)
        {
            SeckillExecution seckillExecution = new SeckillExecution(seckillid,killStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false,seckillExecution);
        }


    }
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public SeckillResult<Long> time()
    {
        Date now  =new Date();
        return new SeckillResult(true,now.getTime());
    }

}
