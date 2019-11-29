package cn.math.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_user")//关联表名
public class User implements Serializable {

    @Id//主键标注
    @GeneratedValue(strategy = GenerationType.IDENTITY)//数据库自动管理主键自增
    private Long id;
    @NotNull(message = "用户名不能为空")
    private String username;
    private String password;
    private boolean is_account_non_expired;
    private boolean is_account_non_locked;
    private boolean is_credentials_non_expired;
    private boolean is_enabled;
}
