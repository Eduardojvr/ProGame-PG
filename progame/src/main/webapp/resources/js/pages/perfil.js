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



let tes = new Vue({
    el : '#usuario',
    data : {
        dados : [
            {
                nomeUsuario : '',
                matricula : '',
                idTipoPerfil : '',
                idPersonagem : '',
                email : '',
                pontuacao : '',
                level : '',
                nomePersonagem : ''
            }
        ]
    },
    created : function(){
        const vm = this;
        vm.getUser();

    },
    methods : {
        getUser : function(){
            const vm = this;
            axios.get('../rs/user/getUser').then(function(response) {
                vm.dados[0].nomeUsuario = response.data["nomeUsuario"];
                vm.dados[0].matricula = response.data["matricula"];
                vm.dados[0].idTipoPerfil  = response.data["idTipoPerfil"];
                // vm.dados[0].idPersonagem  = response.data["idPersonagem"];
                vm.dados[0].email = response.data["email"];
                vm.dados[0].pontuacao  = response.data["pontuacao"];
                vm.dados[0].level  = response.data["level"];
                vm.dados[0].nomePersonagem = sessionStorage.getItem('nomePersonagem');
                // sessionStorage.setItem('idPersonagem', vm.dados[0].idPersonagem);
            });
        }

    }
    
 });


let a = new Vue({
    el : '#personagem',
    data : {
        idPersonagem : '',
        nomePersonagem : '',
        imgPersonagem : ''
    },
    mounted : function(){
            const vm = this;
            vm.idPersonagem = sessionStorage.getItem('idPersonagem');
            vm.imgPersonagem = sessionStorage.getItem('imgPersonagem');
            // Apagar
                // sessionStorage.setItem('nomePersonagem','Aisha');
                // sessionStorage.setItem('imgPersonagem', sessionStorage.getItem('imgPersonagem'));
            if(vm.idPersonagem != null){
                axios.get('../rs/personagem/getPersonagem/'+vm.idPersonagem).then(function(response) {
                    vm.nomePersonagem = response.data["nomePersonagem"];
                    vm.imgPersonagem = response.data["imgPersonagem"];
                    sessionStorage.setItem('nomePersonagem', vm.nomePersonagem);
                });
            }
    }
 });


let t = new Vue({
    el : '#gerenciapersonagem',
    data : {
        nomeUsuario : ''    
    },
    created : function(){
        const vm = this;
        sessionStorage.setItem('vitorias', 90);
        sessionStorage.setItem('perdas', 10);

        axios.get('../rs/user/getUser').then(function(response) {
            vm.nomeUsuario = response.data["nomeUsuario"];
        });

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
            if(sessionStorage.getItem('idPersonagem') == 7){
                $("#modalRelatedContent").modal("show");
            }
            if(sessionStorage.getItem('escolheu') == 1){
                sessionStorage.setItem('escolheu', 0);
                $("#modalFeedback").modal("show");
            }

    }
    
 });

grafico();

 