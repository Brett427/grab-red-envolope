package org.sysu.enums;

public enum killStateEnum {

    SUCCESS(1,"success"),
    END(0,"kill end"),
    REPEATKILL(-1,"repeat kill"),
    INNER_ERROR(-2,"INNER_ERROR"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;
    private String stateinfo;

    killStateEnum(int index,String stateinfo) {
        this.stateinfo = stateinfo;
    }

    public int getState() {
        return state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public static killStateEnum stateOf(int index)
    {
        for(killStateEnum state:values())
        {
            if(state.getState()==index)
            {
                return state;
            }
        }
        return null;
    }
}
