package es.uca.iw.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import net.bytebuddy.implementation.bind.annotation.Default;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appuser")
public class User extends AbstractEntity{

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String hashedPassword;

    
    private String tipousuario;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    /*
    @Lob
    @Column(length = 1000000)
    private byte[] profilePicture;
    */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getTipoUsuario(){
        return tipousuario;
    }

    public void setTipoUsuario(String tipousuario){
        this.tipousuario = tipousuario;
    }

    /*
    public byte[] getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
    */
}
