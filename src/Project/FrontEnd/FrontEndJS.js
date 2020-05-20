var socket = io.connect({transports: ['websocket']});
socket.on('gameState', draw);

var c = document.getElementById("myCanvas");
var ctx = c.getContext("2d");
ctx.globalCompositeOperation = 'source-over';

function draw(event) {
    var gs = JSON.parse(event);

    loadGame();
    //{name: {x: 1, y: 1, token: 1}}

    for(var player in gs){
        if(player === socket.id) {
            setPlayer(gs[player]["x"], gs[player]["y"], "#FF4500")
        }else{
            setPlayer(gs[player]["x"], gs[player]["y"], "#0080A0")
        }
        if(parseInt(gs[player]["tokens"]) < 10) {
            setToken1(gs[player]["x"], gs[player]["y"], gs[player]["tokens"]);
        }else{
            setToken10(gs[player]["x"], gs[player]["y"], gs[player]["tokens"]);
        }
    }
}

function setPlayer(x, y, color) {
    ctx.fillStyle = color;
    ctx.fillRect(x * 32, y * 32, 32, 32);
}

function setToken1(x, y, score){
    ctx.font = "28px Arial";
    ctx.fillStyle = "black";
    ctx.fillText(score.toString(), x * 32 + 8, y * 32 + 26)
}

function setToken10(x, y, score){
    ctx.font = "28px Arial";
    ctx.fillStyle = "black";
    ctx.fillText(score.toString(), x * 32, y * 32 + 26)
}

function loadGame(){
    console.log("REACHED")
    ctx.clearRect(0, 0, 1248, 640);
    ctx.strokeStyle = '#000000';
    for(var i = 0; i < 40; i++){
        ctx.beginPath();
        ctx.moveTo(i * 32, 0);
        ctx.lineTo(i * 32, 640);
        ctx.stroke();
    }
    for(var j = 0; j < 21; j++){
        ctx.beginPath();
        ctx.moveTo(0, j * 32);
        ctx.lineTo(1248, j * 32);
        ctx.stroke();
    }
}

function handleEvent(event){
    if(event.key === "w" || event.key === "ArrowUp"){
        socket.emit("keyStates", "w");
    }else if(event.key === "a" || event.key === "ArrowLeft"){
        socket.emit("keyStates", "a");
    }else if(event.key === "s" || event.key === "ArrowDown"){
        socket.emit("keyStates", "s");
    }else if(event.key === "d" || event.key === "ArrowRight"){
        socket.emit("keyStates", "d");
    }
}

document.addEventListener("keydown", function (event) {
    handleEvent(event);
});