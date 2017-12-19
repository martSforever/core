package com.martsforever.core.template.requestParam;

public class ParamTarget {
    private String name;
    private String val;
    private Integer seq;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "ParamTarget{" +
                "name='" + name + '\'' +
                ", val='" + val + '\'' +
                ", seq=" + seq +
                '}';
    }
}
