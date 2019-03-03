function draw() {
    var ctx = document.getElementById('canvas').getContext('2d');
    var img = new Image();
    img.onload = function() {
        for (var i = 0; i < 4; i++) {
            for (var j = 0; j < 3; j++) {
                ctx.drawImage(img, j * 32 + 16, i * 32 + 16, 32, 32);
            }
        }
    };
    img.src = 'floor.png';
}