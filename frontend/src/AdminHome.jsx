import React from 'react'
import { useNavigate } from 'react-router-dom'

export default function AdminHome({ user, onLogout }) {
  const navigate = useNavigate()

  const cards = [
    { title: 'CRM', description: 'Manage customers, locations, and jobs', path: '/crm' },
    { title: 'Scheduling', description: 'View appointments and dispatch board', path: '/scheduling' },
    { title: 'Reports', description: 'Monitor operations and revenue health', path: '/reports' }
  ]

  return (
    <div className="app-shell">
      <header className="topbar">
        <div>
          <p className="eyebrow">ServiceFlow OS</p>
          <h1>Admin Home</h1>
        </div>
        <div className="topbar-actions">
          <span className="status-pill">Signed in as {user?.username || 'admin'}</span>
          <button type="button" onClick={onLogout}>Log out</button>
        </div>
      </header>

      <main className="dashboard-grid">
        <section className="card hero-card">
          <p className="eyebrow">Operations overview</p>
          <h2>Run your home-services business from one clean command center.</h2>
          <p>Coordinate bookings, dispatch, and customer records with a modern admin workspace.</p>
        </section>

        <section className="cards-grid">
          {cards.map(card => (
            <div key={card.title} className="mini-card admin-card">
              <h4>{card.title}</h4>
              <p>{card.description}</p>
              <button type="button" onClick={() => navigate(card.path)}>Open</button>
            </div>
          ))}
        </section>
      </main>
    </div>
  )
}
