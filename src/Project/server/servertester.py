import requests


joinendpoint = "http://localhost:8080/join"
leaveendpoint = "http://localhost:8080/leave"

requests.post(url=joinendpoint, data={"username": "Joe"})
requests.post(url=joinendpoint, data={"username": "Bob"})
requests.post(url=joinendpoint, data={"username": "Bill"})
requests.post(url=joinendpoint, data={"username": "Jim"})
requests.post(url=joinendpoint, data={"username": "Porky"})

requests.post(url=leaveendpoint, data={"username": "Bill"})
requests.post(url=leaveendpoint, data={"username": "Bob"})
