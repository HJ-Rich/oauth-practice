import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from './Home';
import OAuthSlackCallback from './OAuthSlackCallback';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" exact element={<Home/>}></Route>
                <Route path="/oauth/slack" exact element={<OAuthSlackCallback/>}></Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
