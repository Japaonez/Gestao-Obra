import { useEffect, useState } from "react";
import { Link, useParams, useHistory } from "react-router-dom";
import "./styles.css";
import logoImage from "../../assets/images/builder.svg";
import api from "../../services/api";

const Proprietario: React.FC = () => {
  const [codigo, setCodigo] = useState(null);
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");

  const { propId } = useParams() as any;

  const token = localStorage.getItem("token");

  const history = useHistory();

  async function getProprietario() {
    try {
      const response = api.get(`v1/gto/proprietarios/${propId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setCodigo((await response).data.codigo);
      setNome((await response).data.nome);
      setEmail((await response).data.email);
      setCpf((await response).data.cpf);
    } catch (error) {
      alert("Erro na busca do proprietário. Tente novamente");
      history.push("/proprietarios");
    }
  }

  useEffect(() => {
    if (propId == "0") return;
    else getProprietario();
  }, [propId]);

  async function saveOrUpdate(e: any) {
    e.preventDefault();

    const data = {
      codigo,
      nome,
      email,
      cpf,
    };

    try {
      if (propId == "0") {
        await api.post(`v1/gto/proprietarios`, data, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
      } else {
        data.codigo = codigo;
        await api.post(`v1/gto/proprietarios`, data, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
      }
      history.push("/proprietarios");
    } catch (error) {
      alert(
        "Erro na inclusão do proprietário. " + error + " Tente noovamente!"
      );
    }
  }

  return (
    <div className="new-prop-container">
      <div className="content">
        <section className="form">
          <h2>Cadastro de Proprietários</h2>
          <img src={logoImage} alt="Gestão de Obras" />
          <h3>{propId == "0" ? "Adicionar" : "Atualizar"} Proprietário</h3>
          <p>
            Entre com as informações do proprietário e clique em Adicionar! Ou
            clique em Listar para ver os proprietários cadastrados.
          </p>
        </section>
        <form onSubmit={saveOrUpdate}>
          <input
            placeholder="Nome do Proprietário"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
          />
          <input
            placeholder="E-mail do Proprietário"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            placeholder="CPF do Proprietário"
            value={cpf}
            onChange={(e) => setCpf(e.target.value)}
          />
          <button className="button" type="submit">
            {propId == "0" ? "Adicionar" : "Atualizar"}
          </button>
          <Link to="/proprietarios">
            <button className="button"> Listar</button>
          </Link>
        </form>
      </div>
    </div>
  );
};

export default Proprietario;
