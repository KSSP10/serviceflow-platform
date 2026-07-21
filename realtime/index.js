const express = require('express');
const http = require('http');
const WebSocket = require('ws');

const app = express();
const port = process.env.PORT || 3001;

app.get('/', (req, res) => {
  res.send('Realtime service OK');
});

const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

wss.on('connection', (ws) => {
  ws.send(JSON.stringify({ type: 'welcome', message: 'Realtime connected' }));
  ws.on('message', (message) => {
    console.log('Received WS message:', message.toString());
  });
});

server.listen(port, () => {
  console.log(`Realtime service listening on http://0.0.0.0:${port}`);
});
