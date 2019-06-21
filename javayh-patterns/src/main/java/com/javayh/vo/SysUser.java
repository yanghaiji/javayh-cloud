package com.javayh.vo;

import lombok.Data;

/**
 * @author Dylan Yang
 * @Description: SysUser
 * @Title: SysUser
 * @ProjectName javayh-oauth2
 * @date 2019/6/21 17:01
 */
@Data
public class SysUser {

    private String role;
    private String userName;
    private String passWord;
    private String userType;

    public SysUser() {
    }

    public SysUser(String role, String userName, String passWord, String userType) {
        this.role = role;
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
    }

    /**
     * 静态内部类,实现建造者模式
     */
    public static class SysUserBuild{
        private String role;
        private String userName;
        private String passWord;
        private String userType;
        public SysUserBuild setRole(String role){
            this.role = role;
            return this;
        }
        public SysUserBuild setUserName(String userName){
            this.userName = userName;
            return this;
        }
        public SysUserBuild setPassWord(String passWord){
            this.passWord = passWord;
            return this;
        }
        public SysUserBuild setUserType(String userType){
            this.userType = userType;
            return this;
        }

        public SysUser build(){
            return new SysUser();
        }
        public SysUser buildTwo(){
            return new SysUser(role,userName,passWord,userType);
        }
    }
}

