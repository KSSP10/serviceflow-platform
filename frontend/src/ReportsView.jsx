import React from 'react'

export default function ReportsView() {
  return (
    <div className="app-shell">
      <header className="topbar">
        <div>
          <p className="eyebrow">Analytics</p>
          <h1>Operations Reports</h1>
        </div>
      </header>

      <main className="dashboard-grid">
        <section className="card">
          <h3>Performance snapshot</h3>
          <div className="cards-grid">
            <div className="mini-card">
              <h4>Jobs today</h4>
              <p>12</p>
            </div>
            <div className="mini-card">
              <h4>Completed</h4>
              <p>8</p>
            </div>
            <div className="mini-card">
              <h4>Revenue</h4>
              <p>$6,240</p>
            </div>
          </div>
        </section>
      </main>
    </div>
  )
}
