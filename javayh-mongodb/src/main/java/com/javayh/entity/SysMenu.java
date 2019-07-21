package com.javayh.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Dylan Yang
 * @Description: SysMenu
 * @Title: SysMenu
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 18:29
 */
@Data
@Document
public class SysMenu implements Serializable {
    @Id
    private String id;
    private String code;
    private String pcode;

    public SysMenu() {
    }

    public SysMenu(String id, String code, String pcode) {
        this.id = id;
        this.code = code;
        this.pcode = pcode;
    }

    public static class SysMenuBuid{
        private String id;
        private String code;
        private String pcode;

        public SysMenuBuid setId(String id) {
            this.id = id;
            return this;
        }

        public SysMenuBuid setCode(String code) {
            this.code = code;
            return this;
        }
        public SysMenuBuid setPcode(String pcode) {
            this.pcode = pcode;
            return this;
        }
        public SysMenu build(){
            return new SysMenu(id,code,pcode);
        }

    }

}

