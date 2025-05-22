import { useNavigate } from "react-router-dom";


import './home.css'

function HomePage() {

        const navigate = useNavigate();


    return (
        <div className="home-container">
            <div className="home-content">
                <h1>Atile Challenge</h1>
                <button className='get-tickets-btn' onClick={() => navigate("/tickets")}>My Tickets</button>
                <button className='create-ticket-btn' onClick={() => navigate("/tickets/create")}>Create Ticket</button>
            </div>
        </div>
    )
}

export default HomePage;