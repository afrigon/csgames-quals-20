#!/usr/bin/env python3
import requests
import random
import uuid 
import json

names = ["Abby", "Abe", "Addie", "Abbott", "Alexis", "Ace", "Alice", "Aero", "Allie", "Aiden", "Alyssa", "AJ", "Amber", "Albert", "Angel", "Alden", "Anna", "Alex", "Annie", "Alfie", "Ariel", "Alvin", "Ashley", "Amos", "Aspen", "Andy", "Athena", "Angus", "Autumn", "Apollo", "Ava", "Archie", "Avery", "Aries", "Baby", "Artie", "Bailey", "Ash", "Basil", "Austin", "Bean", "Axel", "Bella", "Bailey", "Belle", "Bandit", "Betsy", "Barkley", "Betty", "Barney", "Bianca", "Baron", "Birdie", "Baxter", "Biscuit", "Bear", "Blondie", "Beau", "Blossom", "Benji", "Bonnie", "Benny", "Brandy", "Bentley", "Brooklyn", "Billy", "Brownie", "Bingo", "Buffy", "Blake", "Callie", "Blaze", "Camilla", "Blue", "Candy", "Bo", "Carla", "Boomer", "Carly", "Brady", "Carmela", "Brody", "Casey", "Brownie", "Cassie", "Bruce", "Chance", "Bruno", "Chanel", "Brutus", "Chloe", "Bubba", "Cinnamon", "Buck", "Cleo", "Buddy", "Coco", "Buster", "Cookie", "Butch", "Cricket", "Buzz", "Daisy", "Cain", "Dakota", "Captain", "Dana", "Carter", "Daphne", "Cash", "Darla", "Casper", "Darlene", "Champ", "Delia", "Chance", "Delilah", "Charlie", "Destiny", "Chase", "Diamond", "Chester", "Diva", "Chewy", "Dixie", "Chico", "Dolly", "Chief", "Duchess", "Chip", "Eden", "CJ", "Edie", "Clifford", "Ella", "Clyde", "Ellie", "Coco", "Elsa", "Cody", "Emma", "Colby", "Emmy", "Cooper", "Eva ", "Copper", "Faith", "Damien", "Fanny", "Dane", "Fern", "Dante", "Fiona", "Denver", "Foxy", "Dexter", "Gabby", "Diego", "Gemma", "Diesel", "Georgia", "Dodge", "Gia ", "Drew", "Gidget", "Duke", "Gigi", "Dylan", "Ginger", "Eddie", "Goldie", "Eli", "Grace", "Elmer", "Gracie", "Emmett", "Greta", "Evan", "Gypsy", "Felix", "Hailey", "Finn", "Hannah", "Fisher", "Harley", "Flash", "Harper", "Frankie", "Hazel", "Freddy", "Heidi", "Fritz", "Hershey", "Gage", "Holly", "George", "Honey", "Gizmo", "Hope", "Goose", "Ibby", "Gordie", "Inez", "Griffin", "Isabella", "Gunner", "Ivy ", "Gus", "Izzy", "Hank", "Jackie", "Harley", "Jada", "Harvey", "Jade", "Hawkeye", "Jasmine", "Henry", "Jenna", "Hoss", "Jersey", "Huck", "Jessie", "Hunter", "Jill", "Iggy", "Josie", "Ivan", "Julia", "Jack", "Juliet", "Jackson", "Juno", "Jake", "Kali", "Jasper", "Kallie", "Jax", "Karma", "Jesse", "Kate", "Joey", "Katie", "Johnny", "Kayla", "Judge", "Kelsey", "Kane", "Khloe", "King", "Kiki", "Kobe", "Kira", "Koda", "Koko", "Lenny", "Kona", "Leo", "Lacy", "Leroy", "Lady", "Levi", "Layla", "Lewis", "Leia", "Logan", "Lena", "Loki", "Lexi", "Louie", "Libby", "Lucky", "Liberty", "Luke", "Lily", "Marley", "Lizzy", "Marty", "Lola", "Maverick", "London", "Max", "Lucky", "Maximus", "Lulu", "Mickey", "Luna", "Miles", "Mabel", "Milo", "Mackenzie", "Moe", "Macy", "Moose", "Maddie", "Morris", "Madison", "Murphy", "Maggie", "Ned", "Maisy", "Nelson", "Mandy", "Nero", "Marley", "Nico", "Matilda", "Noah", "Mattie", "Norm", "Maya", "Oakley", "Mia ", "Odie", "Mika", "Odin", "Mila", "Oliver", "Miley", "Ollie", "Millie", "Oreo", "Mimi", "Oscar", "Minnie", "Otis", "Missy", "Otto", "Misty", "Ozzy", "Mitzi", "Pablo", "Mocha", "Parker", "Molly", "Peanut", "Morgan", "Pepper", "Moxie", "Petey", "Muffin", "Porter", "Mya ", "Prince", "Nala", "Quincy", "Nell", "Radar", "Nellie", "Ralph", "Nikki", "Rambo", "Nina", "Ranger", "Noel", "Rascal", "Nola", "Rebel", "Nori", "Reese", "Olive", "Reggie", "Olivia", "Remy", "Oreo", "Rex", "Paisley", "Ricky", "Pandora", "Rider", "Paris", "Riley", "Peaches", "Ringo", "Peanut", "Rocco", "Pearl", "Rockwell", "Pebbles", "Rocky", "Penny", "Romeo", "Pepper", "Rosco", "Phoebe", "Rudy", "Piper", "Rufus", "Pippa", "Rusty", "Pixie", "Sam", "Polly", "Sammy", "Poppy", "Samson", "Precious", "Sarge", "Princess", "Sawyer", "Priscilla", "Scooby", "Raven", "Scooter", "Reese", "Scout", "Riley", "Scrappy", "Rose", "Shadow", "Rosie", "Shamus", "Roxy", "Shiloh", "Ruby", "Simba", "Sadie", "Simon", "Sage", "Smoky", "Sally", "Snoopy", "Sam ", "Sparky", "Samantha", "Spencer", "Sammie", "Spike", "Sandy", "Spot", "Sasha", "Stanley", "Sassy", "Stewie", "Savannah", "Storm", "Scarlet", "Taco", "Shadow", "Tank", "Sheba", "Taz", "Shelby", "Teddy", "Shiloh", "Tesla", "Sierra", "Theo", "Sissy", "Thor", "Sky ", "Titus", "Smokey", "TJ", "Snickers", "Toby", "Sophia", "Trapper", "Sophie", "Tripp", "Star", "Tucker", "Stella", "Tyler", "Sugar", "Tyson", "Suki", "Vince", "Summer", "Vinnie", "Sunny", "Wally", "Sweetie", "Walter", "Sydney", "Watson", "Tasha", "Willy", "Tessa", "Winston", "Tilly", "Woody", "Tootsie", "Wrigley", "Trixie", "Wyatt", "Violet", "Yogi", "Willow", "Yoshi", "Winnie", "Yukon", "Xena", "Zane" "Zelda", "Zeus" "Zoe", "Ziggy"]

api = "https://dog.ceo/api/breeds/image/random/1"

dogs = []
dog_count = 50
out_file = "dogs.json"

for i in range(dog_count):
    image_json = requests.get(api).json()
    image = image_json['message'][0]

    name = random.choices(names)[0]

    id = uuid.uuid4()
    dogs += [{
        'id': str(id),
        'name': name,
        'image': image
    }]
    
f = open(out_file, "w")
json.dump(dogs, f)
f.close()

