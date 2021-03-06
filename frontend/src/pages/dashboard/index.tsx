import BarChart from "../../components/basics/BarChart";
import Sidebar from "../../components/basics/sidebar";
import DonutChart from "../../components/basics/DonutChart";

const dashboard: React.FC = () => {
  return (
    <>
      <Sidebar />
      <div className="container">
        <h1 className="text-primary py-3">Dashboard de Obras</h1>
        <div className="row px-3">
          <div className="col-sm-6">
            <h5 className="text-center text-secondary">Lançamentos(%)</h5>
            <BarChart />
          </div>
          <div className="col-sm-6">
            <h5 className="text-center text-secondary">Lançamentos(%)</h5>
            <DonutChart />
          </div>
          <div className="py-3">
            <h2 className="text-primary">Todos os lançamentos</h2>
          </div>
        </div>
      </div>
    </>
  );
};
export default dashboard;
