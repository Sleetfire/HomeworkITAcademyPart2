package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "test", name = "audit_users")
public class AuditUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn
    private UserEntity author;

    @ManyToOne
    @JoinColumn
    private UserEntity user;

    public AuditUserEntity(Long id, LocalDateTime dtCreate, String text, UserEntity author, UserEntity user) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.author = author;
        this.user = user;
    }

    public AuditUserEntity() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public String getText() {
        return text;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public UserEntity getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditUserEntity that = (AuditUserEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(text, that.text)
                && Objects.equals(author, that.author)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtCreate, text, author, user);
    }

    @Override
    public String toString() {
        return "AuditUser{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", user=" + user +
                '}';
    }

    public static class Builder {
        private Long id;
        private LocalDateTime dtCreate;
        private String text;
        private UserEntity author;
        private UserEntity user;

        private Builder() {

        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setAuthor(UserEntity author) {
            this.author = author;
            return this;
        }

        public Builder setUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public AuditUserEntity build() {
            return new AuditUserEntity(this.id, this.dtCreate, this.text, this.author, this.user);
        }
    }

}
