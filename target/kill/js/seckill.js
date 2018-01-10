//模块化
var seckill =
    {
        //封装秒杀相关AJAX的URL
        URL:{
            now: function () {
                return "/kill/time/now";
            },
            exposer:function (seckillId) {
                return '/kill/'+seckillId+'/exposer';
            },
            execution: function (seckillid,md5) {

                return '/kill/'+seckillid+'/'+md5+'/execution';
            }
        },

        validdatePhone :function (phone) {
          if(phone&& phone.length == 11 &&!isNaN(phone))
          {
              return true;
          }
          else
          {
              return false;
          }
        },

        handerkill: function (seckillId,node) {
            node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">begin</button>');

            $.post(seckill.URL.exposer(seckillId),{},function (result) {
                //回调函数中,执行交互流程
                console.log(result);
                if(result && result['success'])
                {
                    var exposer = result['data'];
                    if(exposer['exposed'])
                    {
                        //开启秒杀
                        //获取秒杀地址
                        var md5 = exposer['md5'];
                        var killurl = seckill.URL.execution(seckillId,md5);
                        console.log("killURl"+killurl);
                        // 绑定一次点击事件
                        $('#killBtn').one('click',function () {
                            //绑定执行秒杀请求操作.
                            //1 .禁用按钮
                            $(this).addClass('disabled');
                            //2 . 发送秒杀请求执行秒杀
                            alert(killurl)
                            $.post(killurl, {}, function (result) {
                                if (result && result['success']) {
                                    var killResult = result['data'];
                                    var state = killResult['state'];
                                    var stateInfo = killResult['stateInfo'];
                                    //显示秒杀结果
                                    node.html('<span class="label label-success">' + stateInfo + '</span>');
                                }
                            });
                        })
                        node.show();
                    }
                    else
                    {
                        var now  =exposer['now'];
                        var start=  exposer['start'];
                        var end = exposer['end'];
                        // 重新进入计时逻辑
                        seckill.countdown1(seckillId,now,start,end);
                    }
                }
                else
                {
                    console.log('result'+result);
                }
            })
        },
        countdown1: function (seckillid,nowtime,starttime,endtime) {

            var seckillBox =$('#seckill-box');
            if(nowtime>endtime)
            {
                //秒杀结束.
                seckillBox.html('has been over');
            }
            else if (nowtime<starttime)
            {
                //秒杀未开始,计时事件绑定
                var killTime = new Date(starttime+1000);
                seckillBox.countdown(killTime,function (event) {
                    //时间格式
                    var format = event.strftime('倒计时: %D天 %H时 %M分 %S秒');
                    seckillBox.html(format);
                    //时间完成后的回调事件
                }).on('finish.countfown',function () {
                    //获取秒杀地址,控制显示逻辑,执行秒杀

                    seckill.handerkill(seckillid,seckillBox);
                })
            }
            else
            {
                console.log('seckillId'+ seckillid);
                seckill.handerkill(seckillid,seckillBox);
            }
        },

        //详情页秒杀逻辑
        detail:{
            //详情页初始化
            init: function(params)
            {
                //手机验证和登录,计时交互
                //规划交互流程
                //在cookie中查找手机号
                var killphone = $.cookie('killphone');

                //验证手机号
                if(!seckill.validdatePhone(killphone))
                {
                    //若没有登录
                    //控制输出
                    var killphoneModel = $('#killPhoneModal');
                    //显示弹出层
                    killphoneModel.modal(
                        {
                            show: true, //显示弹出层
                            backdrop: false,
                            keyboard: false
                        }
                    );
                    $('#killPhoneBtn').click(function() {
                        var inputPhone = $('#killPhoneKey').val();
                        console.log(inputPhone)
                        if(seckill.validdatePhone(inputPhone))
                        {
                            $.cookie('killphone',inputPhone,{expires:7});
                            //刷新页面
                            window.location.reload();
                        }
                        else
                        {
                            $('#killPhoneMessage').hide().html('<label class="label label-danger"> error phone</label>').show(300);
                        }
                    })
                }

                //已经登录
                //计时逻辑
                //result是回调函数返回的参数,也就是后端返回的值
                var startTime = params['startTime'];
                var endTime = params['endTime'];
                var seckillId = params['seckillid'];
                console.log(seckillId);
                $.get(seckill.URL.now(),{},function (result) {
                    if(result && result['success'])
                    {
                        var timenow = result['data'];
                        //时间判断,根据系统时间和秒杀开始时间做流程控制
                        seckill.countdown1(seckillId,timenow,startTime,endTime);
                    }
                    else
                    {
                        console.log('result'+result);
                    }
                })
            }
        }
    }

