package com.vmall.pojo.vo;

public class Property {
    private long propertiesId;//属性选项Id
    private long propertyId;//属性Id

    public Property(){}
    public Property(long propertiesId,long propertyId){
        this.propertiesId=propertiesId;
        this.propertyId=propertyId;
    }
    public long getPropertiesId() {
        return propertiesId;
    }

    public void setPropertiesId(long propertiesId) {
        this.propertiesId = propertiesId;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }
}
