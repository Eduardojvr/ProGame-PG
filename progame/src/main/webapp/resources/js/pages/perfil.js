function grafico(){
    var ctx = document.getElementById('myChart');  
    var myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['perdas', 'vitorias'],
            datasets: [{
                label: ['Vitorias', 'Derrotas'],
                data: [sessionStorage.getItem('vitorias'), sessionStorage.getItem('perdas')],
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
}

let a = new Vue({
    el : '#imagem',
    data : {
        idPersonagem : sessionStorage.getItem("idPersonagem"),
        nomePersonagem : sessionStorage.getItem("nomePersonagem"),
        imgPersonagem : sessionStorage.getItem("imgPersonagem")
    },
    created : function(){
        const vm = this;
        vm.personagem();
    },
    methods : {
        personagem : function(){
            const vm = this;
            axios.get('/progame/rs/personagem/getPersonagem/'+sessionStorage.getItem('idPersonagem')).then(function(response) {
                sessionStorage.setItem('idPersonagem', response.data["idPersonagem"]);
                sessionStorage.setItem('nomePersonagem', response.data["nomePersonagem"]);
                sessionStorage.setItem('imgPersonagem', response.data["imgPersonagem"]);
                //alert(sessionStorage.getItem('imgPersonagem'));
            });
        }
    }
    
 });


let t = new Vue({
    el : '#gerenciapersonagem',
    data : {
        level : sessionStorage.getItem("level")
    },
    created : function(){
        const vm = this;
        document.getElementById('nome').innerHTML = sessionStorage.getItem('nomeUsuario');
        document.getElementById('email').innerHTML =  sessionStorage.getItem('email');
        document.getElementById('matricula').innerHTML = sessionStorage.getItem('matricula');
        document.getElementById('pontos').innerHTML = sessionStorage.getItem('pontuacao');
        document.getElementById('personagem').innerHTML = sessionStorage.getItem('nomePersonagem');
        document.getElementById('level').innerHTML =  sessionStorage.getItem('level');
        sessionStorage.setItem('vitorias', 90);
        sessionStorage.setItem('perdas', 10);

    },
    methods : {
        
        redireciona : function(link){
            window.location.href = link;
        }
     
    
    },
    mounted : function(){
            if(sessionStorage.getItem('login') == 1){
                sessionStorage.setItem('login', 0);
                $("#modalFeedbackBomJogo").modal("show");
            }
            if(sessionStorage.getItem('idPersonagem') == 2){
                $("#modalRelatedContent").modal("show");
            }
            if(sessionStorage.getItem('escolheu') == 1){
                sessionStorage.setItem('escolheu', 0);
                $("#modalFeedback").modal("show");
            }
    }
    
 });

grafico();
document.getElementById("nomeJogador").innerHTML = sessionStorage.getItem('nomeUsuario');


 