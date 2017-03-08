package top.wangjingxin.model;

import lombok.Data;

/**
 * Created by 17854 on 2016/11/26.
 */
@Data
public class User
{
    private String user_id;
    private String user_name;
    private String user_password;
    private String user_gender;
    private int if_set;
}
