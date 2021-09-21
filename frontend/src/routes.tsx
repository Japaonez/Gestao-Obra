import { Route, BrowserRouter, Switch } from 'react-router-dom'
import Home from './pages/home';
import Login from './pages/login';
import Props from './pages/proprietarios';
import Prop from './pages/proprietario';
const Routes = () => {
    return(
        <BrowserRouter>
            <Switch>
                <Route component={ Home } path="/" exact />
                <Route component={ Login } path="/login" />
                <Route component={ Props } path="/proprietarios" />
                <Route component={ Prop } path="/proprietario/:propId" />
            </Switch>
        </BrowserRouter>
    )
}
export default Routes;