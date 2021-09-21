import { useEffect, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import {
  FiPower,
  FiEdit,
  FiTrash2,
  FiArrowRightCircle,
  FiArrowLeftCircle,
} from "react-icons/fi";
import { Table } from "react-bootstrap";
import logoImage from "../../assets/images/builder.svg";
import "./styles.css";
import api from "../../services/api";

interface iProprietarios {
  codigo: number;
  nome: string;
  email: string;
  cpf: string;
}

const Proprietarios: React.FC = () => {
  const [proprietarios, setProprietarios] = useState<iProprietarios[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem("username");
  const token = localStorage.getItem("token");

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push("/");
  }

  async function editProprietario(id: any) {
    try {
      history.push(`proprietario/${id}`);
    } catch (error) {
      alert(
        "Erro na edição do proprietário. " + error + "Tente novamente"
      );
    }
  }

  async function deleteProprietario(id: any) {
    try {
      await api.delete(`v1/gto/proprietario/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      alert("Esclusão com sucesso");
      loadProprietarios();
    } catch (error) {
      alert(
        "Erro na edição do proprietário. " + error + "Tente novamente"
      );
    }
  }

  async function loadProprietarios() {
    const response = await api.get("v1/gto/proprietarios", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
      params: {
        page: page,
        limite: 4,
        direction: "asc",
      },
    });

    setProprietarios(response.data._embedded.proprietarioDTOList);
  }

  useEffect(() => {
    loadProprietarios();
  }, [page]);

  return (
    <div className="proprietario-container">
      <header>
        <img src={logoImage} alt="Gestão de Obras" />
        <span>
          Bem-vindo, <strong>{username?.toUpperCase()}</strong>!
        </span>
        <div className="subheader">
          <button
            onClick={() => {
              setPage(page - 1);
              loadProprietarios();
            }}
            type="button"
          >
            <FiArrowLeftCircle size={18} color="#251FC5" />
          </button>
          <Link className="button" to="/proprietario/0">
            Novo Proprietario!
          </Link>
          <button
            onClick={() => {
              setPage(page + 1);
              loadProprietarios();
            }}
            type="button"
          >
            <FiArrowRightCircle size={18} color="#251FC5" />
          </button>
          <button onClick={logout} type="button">
            <FiPower size={18} color="#251FC5" />
          </button>
        </div>
      </header>

      <h1>Proprietários Cadastrados</h1>

      <Table striped bordered hover className="text-center">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>E-mail</th>
            <th>CPF</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {proprietarios.map((p) => (
            <tr key={p.codigo}>
              <td>{p.codigo}</td>
              <td>{p.nome}</td>
              <td>{p.email}</td>
              <td>{p.cpf}</td>
              <td>
                <button
                  onClick={() => editProprietario(p.codigo)}
                  type="button"
                >
                  <FiEdit size={20} color="#251FC5" />
                </button>

                <button
                  onClick={() => deleteProprietario(p.codigo)}
                  type="button"
                >
                  <FiTrash2 size={20} color="#251FC5" />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};
export default Proprietarios;
