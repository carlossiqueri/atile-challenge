import HomePage from './pages/home/home'

import { HashRouter, Route, Routes } from "react-router-dom";
import Tickets from './pages/tickets/tickets';
import CreateTicket from './pages/tickets/create-ticket';

function App() {

  return (
    <HashRouter>
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='/tickets' element={<Tickets />} />
        <Route path='/tickets/create' element={<CreateTicket />} />
      </Routes>
    </HashRouter>
  )
}

export default App
