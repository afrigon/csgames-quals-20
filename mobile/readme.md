# CSGames Qualifications 2020

## Mobile - Good boi or not

Good boi or not is the newest and hotest app of the moment. You absolutely need to help the CS organisers to hop on that trend. The idea is very simple. Show the dogs to the users, then let them decide. The goodest boi of them all will deserve a crown and a bone and you'll have to let the world know.

### Features

- Displaying dogs
- Ranking a dog as a good boi or a bad boi
- Displaying the ranking of the goodest bois
- Light and Dark mode
- Language support

### Format

Please provide your entire project with everything required to compile it. Include screenshots to showcase your progress and show off your favorite doggos.

### API Specification

`http://csgames-quals.frigon.app:9000`

#### List Dogs

`GET /doggo`

```
Content-Type: application/json
```

```json
[
  {
    "id": "577b29bc-e6dd-4280-ab8b-ca0ac0b9c1a7",
    "name": "Romeo",
    "image": "https://images.dog.ceo/breeds/papillon/n02086910_1971.jpg"
  },
  ...
]
```

#### Rank dog

##### Good Boi

`PATCH /doggo/:id/good 204 no-content`

##### Bad Boi

`PATCH /doggo/:id/bad 204 no-content`

#### Get Dogs Ranking

`GET /doggo/ranks`

```
Content-Type: application/json
```

```json
{
    "577b29bc-e6dd-4280-ab8b-ca0ac0b9c1a7": {
        "good_boi": 69,
        "bad_boi": 0
    },
    ...
}
```

