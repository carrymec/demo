package com.example.demo.common.tree;

import java.util.List;

/**
 * @CLASSNAME TreeEntity
 * @DESCRIPTION 描述
 * @AUTHOR chen
 * @DATE 2018-10-15 17:53
 **/
public interface TreeEntity<E> {

    Integer getDepartmentId();

    Integer getParentId();

    void setChildList(List<E> childList);//对应实体类中的 List<E> childList;

}
