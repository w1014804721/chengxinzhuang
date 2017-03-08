package top.wangjingxin.model;

import lombok.Data;

/**
 * Created by 17854 on 2016/11/26.
 */
@Data
public class Task
{
    private String user_id;
    private String task;
    private String task_id;
    private int if_over = 0;
    private Richang richang;
}
