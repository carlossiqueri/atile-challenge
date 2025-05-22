import { useEffect, useState } from "react"
import "./tickets.css"
import axios from "axios"

function Tickets() {

    const [tickets, setTickets] = useState([])

    useEffect(() => {
        function fetchTickets() {
            axios
                .get("http://localhost:8080/tickets")
                .then((res) => {
                    setTickets(res.data)
                })
                .catch((err) => {
                    console.log(err)
                })
        }
        fetchTickets()
    }, [])

    console.log(tickets)
    return (
        <div className="tickets-container">
            <div className="tickets-content">
                <h1>Meus Tickets</h1>
                <ul>
                    {tickets.map((ticket, index) => {
                        return (
                            <div className="ticket-field" key={index}>
                                <p>{ticket.id}</p>
                                <p>{ticket.title}</p>
                                <p>{ticket.description}</p>
                                <p>{ticket.status}</p>
                                <p>{ticket.createdAt}</p>
                                <button className="edit-button">Editar</button>
                                <button className="delete-button">Deletar</button>
                            </div>
                        )
                    })}
                </ul>
            </div>
        </div>

    )
}

export default Tickets