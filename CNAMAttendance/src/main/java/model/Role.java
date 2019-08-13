package model;


public class Role
{
    private Long role_id;

    private String type;

    public Role()
    {
    }

    public Long getRole_id()
    {
        return role_id;
    }

    public Role(Long role_id)
    {
        this.role_id = role_id;
    }

    public void setRole_id(Long id)
    {
        this.role_id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String role)
    {
        this.type = role;
    }


}
