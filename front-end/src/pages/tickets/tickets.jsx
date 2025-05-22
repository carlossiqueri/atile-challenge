import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./tickets.css";

function Tickets() {
    const [tickets, setTickets] = useState([]);
    const [editingTicket, setEditingTicket] = useState(null);
    const [editFormData, setEditFormData] = useState({
        title: "",
        description: "",
        status: ""
    });
    const navigate = useNavigate();

    useEffect(() => {
        fetchTickets();
    }, []);

    async function fetchTickets() {
        try {
            const response = await axios.get("http://localhost:8080/tickets");
            setTickets(response.data);
        } catch (err) {
            console.error("Error fetching tickets:", err);
        }
    }

    async function updateTicket(id) {
        try {
            await axios.put(`http://localhost:8080/tickets/${id}`, editFormData);
            setEditingTicket(null);
            alert("Ticket atualizado com sucesso.")
            fetchTickets();
        } catch (err) {
            console.error("Error updating ticket:", err);
        }
    }

    async function deleteTicket(id) {
        const confirmation = confirm("Você tem certeza? Essa ação não pode ser desfeita.")

        if (confirmation) {
            try {
                await axios.delete(`http://localhost:8080/tickets/${id}`);
                alert("Ticket deletado com sucesso.")
                fetchTickets();
            } catch (err) {
                console.error("Error deleting ticket:", err);
            }

        }
    }

    function handleEditClick(ticket) {
        setEditingTicket(ticket.id);
        setEditFormData({
            title: ticket.title,
            description: ticket.description,
            status: ticket.status
        });
    }

    function handleCancelEdit() {
        setEditingTicket(null);
    }

    function handleEditFormChange(e) {
        const { name, value } = e.target;
        setEditFormData(prev => ({
            ...prev,
            [name]: value
        }));
    }

    return (
        <div className="tickets-container">
            <div className="tickets-content">
                <h1>Meus Tickets</h1>
                <ul className="tickets-list">
                    {tickets.map((ticket) => (
                        <li className="ticket-item" key={ticket.id}>
                            <div className="ticket-field">
                                <p><strong>ID:</strong> {ticket.id}</p>
                                <p><strong>Título:</strong> {ticket.title}</p>
                                <p><strong>Descrição:</strong> {ticket.description}</p>
                                <p><strong>Status:</strong> {ticket.status}</p>
                                <p><strong>Criado em:</strong> {new Date(ticket.createdAt).toLocaleString()}</p>

                                <div className="ticket-actions">
                                    <button
                                        className="edit-button"
                                        onClick={() => handleEditClick(ticket)}
                                        disabled={editingTicket !== null}
                                    >
                                        Editar
                                    </button>
                                    <button
                                        className="delete-button"
                                        onClick={() => deleteTicket(ticket.id)}
                                        disabled={editingTicket !== null}
                                    >
                                        Deletar
                                    </button>
                                </div>
                            </div>

                            {editingTicket === ticket.id && (
                                <div className="edit-form">
                                    <h3>Editar Ticket</h3>
                                    <div className="form-group">
                                        <label>Título:</label>
                                        <input
                                            type="text"
                                            name="title"
                                            value={editFormData.title}
                                            onChange={handleEditFormChange}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Descrição:</label>
                                        <textarea
                                            name="description"
                                            value={editFormData.description}
                                            onChange={handleEditFormChange}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Status:</label>
                                        <select
                                            name="status"
                                            value={editFormData.status}
                                            onChange={handleEditFormChange}
                                        >
                                            <option value="OPEN">Aberto</option>
                                            <option value="IN_PROGRESS">Em Progresso</option>
                                            <option value="CLOSED">Fechado</option>
                                        </select>
                                    </div>
                                    <div className="form-actions">
                                        <button
                                            className="confirm-button"
                                            onClick={() => updateTicket(ticket.id)}
                                        >
                                            Confirmar
                                        </button>
                                        <button
                                            className="cancel-button"
                                            onClick={handleCancelEdit}
                                        >
                                            Cancelar
                                        </button>
                                    </div>
                                </div>
                            )}
                        </li>
                    ))}
                </ul>

                <button className="home-button" onClick={() => navigate("/")}>HomePage</button>
            </div>
        </div>
    );
}

export default Tickets;