package com.yjxxt.jxc.query;

import com.yjxxt.jxc.base.BaseQuery;

public class ClientQuery extends BaseQuery {
    private String clientName;
    private String clientPhone;

    public ClientQuery() {
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    @Override
    public String toString() {
        return "ClientQuery{" +
                "clientName='" + clientName + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                '}';
    }
}
