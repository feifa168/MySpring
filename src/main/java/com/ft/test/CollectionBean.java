package com.ft.test;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionBean<E> {
    private List<E> listInt;
    private Set<E> setInt;
    private Map<Integer, E> mapIntStr;
    private Properties prot;

    public List<E> getListInt() {
        return listInt;
    }

    public Set<E> getSetInt() {
        return setInt;
    }

    public Map<Integer, E> getMapIntStr() {
        return mapIntStr;
    }

    public Properties getProt() {
        return prot;
    }

    public void setListInt(List<E> listInt) {
        this.listInt = listInt;
    }

    public void setSetInt(Set<E> setInt) {
        this.setInt = setInt;
    }

    public void setMapIntStr(Map<Integer, E> mapIntStr) {
        this.mapIntStr = mapIntStr;
    }

    public void setProt(Properties prot) {
        this.prot = prot;
    }
}
