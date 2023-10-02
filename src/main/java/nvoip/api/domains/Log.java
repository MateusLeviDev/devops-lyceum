package nvoip.api.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_user")
    private int idUser;

    private String ip;

    @Column(name = "type")
    private int type;

    @Column(name = "alteracao")
    private String alteracao;

    public Log(int idUser, String ip, int type, String alteracao) {
        this.idUser = idUser;
        this.ip = ip;
        this.type = type;
        this.alteracao = alteracao;
    }
}
