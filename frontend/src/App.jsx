import React, { useEffect, useState } from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { getHealth } from './api/health'
import Auth from './Auth'
import Crm from './Crm'
import AdminHome from './AdminHome'
import SchedulingView from './SchedulingView'
import ReportsView from './ReportsView'

export default function App() {
  const [status, setStatus] = useState('unknown')
  const [user, setUser] = useState(null)

  useEffect(() => {
    let mounted = true
    getHealth().then(text => { if (mounted) setStatus(text) }).catch(() => { if (mounted) setStatus('unreachable') })
    return () => { mounted = false }
  }, [])

  function handleLogin(userData) {
    setUser(userData)
  }

  function handleLogout() {
    setUser(null)
  }

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LandingPage status={status} onLogin={handleLogin} user={user} />} />
        <Route path="/login" element={user ? <Navigate to="/admin" replace /> : <Auth onLogin={handleLogin} />} />
        <Route path="/admin" element={user ? <AdminHome user={user} onLogout={handleLogout} /> : <Navigate to="/login" replace />} />
        <Route path="/crm" element={user ? <Crm /> : <Navigate to="/login" replace />} />
        <Route path="/scheduling" element={user ? <SchedulingView /> : <Navigate to="/login" replace />} />
        <Route path="/reports" element={user ? <ReportsView /> : <Navigate to="/login" replace />} />
      </Routes>
    </BrowserRouter>
  )
}

function LandingPage({ status, onLogin, user }) {
  return (
    <div className="app-shell">
      <header className="topbar">
        <div>
          <p className="eyebrow">ServiceFlow OS</p>
          <h1>Operations Console</h1>
        </div>
        <div className="topbar-actions">
          <div className="status-pill">
            <span className="status-dot" />
            Backend: <strong>{status}</strong>
          </div>
          {user ? <button type="button" onClick={() => window.location.href = '/admin'}>Go to admin</button> : <button type="button" onClick={() => window.location.href = '/login'}>Sign in</button>}
        </div>
      </header>

      <main className="dashboard-grid">
        <section className="card hero-card">
          <p className="eyebrow">Modern field operations</p>
          <h2>From intake to dispatch, everything in one place.</h2>
          <p>Manage customers, locations, jobs, and scheduling with a polished admin experience.</p>
        </section>

        <section className="cards-grid">
          <div className="mini-card">
            <h4>Admin workspace</h4>
            <p>Secure dashboard for day-to-day operations.</p>
          </div>
          <div className="mini-card">
            <h4>CRM intake</h4>
            <p>Create customers, service locations, and jobs.</p>
          </div>
          <div className="mini-card">
            <h4>Dispatch board</h4>
            <p>Track appointments and technician assignments.</p>
          </div>
        </section>
      </main>
    </div>
  )
}
