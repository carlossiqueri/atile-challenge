import { useState } from 'react'
import './create-ticket.css'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function CreateTicket() {
    const [formData, setFormData] = useState({
        title: "",
        description: ""
    })
    const [isSubmitting, setIsSubmitting] = useState(false)
    const [submitMessage, setSubmitMessage] = useState("")

    const navigate = useNavigate()


    const handleChange = (e) => {
        const { name, value } = e.target
        setFormData(prev => ({
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        setIsSubmitting(true)
        setSubmitMessage("")

        try {
            await axios.post("http://localhost:8080/tickets", formData);

            setSubmitMessage("Ticket criado com sucesso!");
            setFormData({ titulo: "", descricao: "" });
        } catch (error) {
            console.error("Error:", error);
            setSubmitMessage("Erro ao criar ticket. Tente novamente.");
        } finally {
            setIsSubmitting(false);
        }
    }



    return (
        <div className="create-tickets-container">
            <div className="create-tickets-content">
                <div className="create-ticket">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="title">Título:</label>
                            <input
                                type="text"
                                id="title"
                                name="title"
                                value={formData.title}
                                onChange={handleChange}
                                required
                                maxLength={100}
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="description">Descrição:</label>
                            <textarea
                                id="description"
                                name="description"
                                value={formData.description}
                                onChange={handleChange}
                                required
                                rows={5}
                                maxLength={500}
                            />
                        </div>

                        <button type="submit" disabled={isSubmitting} className='create-btn'>
                            {isSubmitting ? "Enviando..." : "Criar Ticket"}
                        </button>

                        {submitMessage && (
                            <div className={`message ${submitMessage.includes("sucesso") ? "success" : "error"}`}>
                                {submitMessage}
                            </div>
                        )}
                    </form>
                </div>
                <button className='home-btn' onClick={() => navigate("/")}>HomePage</button>
            </div>
        </div>
    )
}

export default CreateTicket;