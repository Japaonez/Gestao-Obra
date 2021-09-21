import { Route, BrowserRouter, Switch } from 'react-router-dom'
import Home from './pages/home';
import Login from './pages/login';
import Proprietario from './pages/proprietario';
const Routes = () => {
    return(
        <BrowserRouter>
            <Switch>
                <Route component={ Home } path="/" exact />
                <Route component={ Login } path="/login" />
                <Route component={ Proprietario } path="/proprietarios" />
            </Switch>
        </BrowserRouter>
    )
}
export default Routes;