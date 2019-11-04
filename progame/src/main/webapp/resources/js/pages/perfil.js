function dadosUsuario(){
    var nome = document.getElementById('nome').innerHTML = sessionStorage.getItem('nomeUsuario');
    var email = document.getElementById('email').innerHTML =  sessionStorage.getItem('email');
    var matricula = document.getElementById('matricula').innerHTML = sessionStorage.getItem('matricula');
    var pontos = document.getElementById('pontos').innerHTML = sessionStorage.getItem('pontos');
    var personagem = document.getElementById('personagem').innerHTML = sessionStorage.getItem('idPersonagem');
    var level = document.getElementById('level').innerHTML =  sessionStorage.getItem('level');
}


     $(document).ready(function() {
         // Javascript method's body can be found in assets/js/demos.js
         demo.initDashboardPageCharts();
 
         demo.showNotification();
 
     });

     function redireciona(){
         window.location.assign('../index.html');
     }
     
     var ctx = document.getElementById('myChart');  
    var myChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ['Derrotas', 'Vitórias'],
        datasets: [{
            label: ['sa', 'asd'],
            data: [25,75],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    }
});

dadosUsuario();
