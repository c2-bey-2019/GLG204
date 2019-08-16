package jpa;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "selectAll",
                query = "SELECT r from Role r"),
        @NamedQuery(
                name = "selectRoleId",
                query = "SELECT r FROM Role r WHERE r.type LIKE :type")

})
public class Role
{
    @Id
    private Long role_id;

    @Column(unique = true)
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
