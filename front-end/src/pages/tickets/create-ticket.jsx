import { useState } from 'react'
import './tickets.css'
import axios from 'axios';

function CreateTicket() {
    const [formData, setFormData] = useState({
        title: "",
        description: ""
    })
    const [isSubmitting, setIsSubmitting] = useState(false)
    const [submitMessage, setSubmitMessage] = useState("")


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

        axios.post("http://localhost:8080/tickets", formData)
        .then((res) => {
            console.log("Success: ", res.data)
            setSubmitMessage("Ticket criado com sucesso.")
            setFormData({ title: "", description: "" })
        })
        .catch((err) => {
            console.error("Error:", err)
            setSubmitMessage("Erro ao criar ticket. Tente novamente.")
        })
        .finally (() => {
            setIsSubmitting(false)
        })
    }



        return (
            <div className="tickets-container">
                <div className="tickets-content">
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

                            <button type="submit" disabled={isSubmitting}>
                                {isSubmitting ? "Enviando..." : "Criar Ticket"}
                            </button>

                            {submitMessage && (
                                <div className={`message ${submitMessage.includes("sucesso") ? "success" : "error"}`}>
                                    {submitMessage}
                                </div>
                            )}
                        </form>
                    </div>
                </div>
            </div>
        )
    }

    export default CreateTicket;