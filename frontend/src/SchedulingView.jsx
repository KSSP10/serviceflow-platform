import React from 'react'

export default function SchedulingView() {
  const jobs = [
    { id: 1, title: 'AC Repair', technician: 'Nina', window: '10:00 - 12:00', status: 'Booked' },
    { id: 2, title: 'Water Heater Install', technician: 'Luis', window: '13:00 - 15:00', status: 'Dispatched' }
  ]

  return (
    <div className="app-shell">
      <header className="topbar">
        <div>
          <p className="eyebrow">Operations</p>
          <h1>Scheduling Board</h1>
        </div>
      </header>

      <main className="dashboard-grid">
        <section className="card">
          <h3>Today’s dispatch queue</h3>
          <div className="cards-grid">
            {jobs.map(job => (
              <div key={job.id} className="mini-card">
                <h4>{job.title}</h4>
                <p>{job.window}</p>
                <p>Technician: {job.technician}</p>
                <p>Status: {job.status}</p>
              </div>
            ))}
          </div>
        </section>
      </main>
    </div>
  )
}
