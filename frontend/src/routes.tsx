import { Route, BrowserRouter } from 'react-router-dom'
import Home from './pages/home';
import Login from './pages/login';
const Routes = () => {
    return(
        <BrowserRouter>
            <Route component={ Home } path="/" exact />
            <Route component={ Login } path="/login" />
        </BrowserRouter>
    )
}
export default Routes;