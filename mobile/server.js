const express = require('express')
const app = express()
const server = require('http').createServer(app)

const port = 8080

const dogs = require("./dogs")

app.disable('x-powered-by')

let ratings = {}

app.get('/doggo', (req, res) => res.json(dogs))
app.get('/doggo/ranks', (req, res) => res.json(ratings))
app.patch('/doggo/:id/good', (req, res) => rate(req.params.id, 1, res))
app.patch('/doggo/:id/bad', (req, res) => rate(req.params.id, -1, res))

const rate = (id, value, res) => {
    if (!dogs.find((dog) => dog.id == id)) {
        return res.status(404).json({ error: `could not find dog with id: ${id}` })
    }

    ratings[id] = ratings[id] || { 'good_boi': 0, 'bad_boi': 0 }
    ratings[id][value > 0 ? 'good_boi' : 'bad_boi'] += value

    return res.status(204).send()
}

app.use((req, res) => res.status(404).send('404'))

server.listen(port)

