let tes = new Vue({
    el : '#usuario',
    data : {
        dados : 
            {
                nomeUsuario : '',
                matricula : '',
                idTipoPerfil : '',
                idPersonagem : '',
                email : '',
                pontuacao : '',
                level : '',
                nomePersonagem : '',
                desativado: ''
            },
            idPersonagem : '',
            nomePersonagem : '',
            imgPersonagem : '',
            nomeUsuario : '',
            level : '',
            progresso: '',
            usuario: {},
            totalvs: 0,
            totalNotificacao: 0,
            mensagens:[],
            defineSenha : {
                matricula: '',
                senhaAntiga: '',
                novaSenha: '',
                senhaTmp: ''
            }        
    },
    created : function(){
        const vm = this;
        vm.getUser();
        axios.get('../rs/user/getUser').then(function(response) {
            vm.usuario = response.data;
            vm.nomeUsuario = response.data["nomeUsuario"];
            vm.calculaProgresso();
            vm.grafico();
        });
        var count = 0;
        axios.get('../rs/user/getUser').then(function(response){
            axios.get('../rs/desafio/todosDesafios/'+response.data["matricula"]).then(function(response){
                while(response.data[count]){
                    count+=1;
                }
                vm.totalvs = count;
                vm.totalNotificacao = 1;
            });
        });
    },
    methods : {
        redireciona : function(link){
            window.location.href = link;
        },
        getUser : function(){
            const vm = this;
            axios.get('../rs/user/getUser').then(function(response) {
                vm.dados.nomeUsuario = response.data["nomeUsuario"];
                vm.dados.matricula = response.data["matricula"];
                vm.dados.idTipoPerfil  = response.data["idTipoPerfil"];
                // vm.dados.idPersonagem  = response.data["idPersonagem"];
                vm.dados.email = response.data["email"];
                vm.dados.pontuacao  = response.data["pontuacao"];
                vm.dados.level  = response.data["level"];
                vm.dados.desativado  = response.data["desativado"];
                vm.dados.nomePersonagem = sessionStorage.getItem('nomePersonagem');
                // sessionStorage.setItem('idPersonagem', vm.dados.idPersonagem);
            });

        },
        grafico : function (){
            const vm = this;
            axios.get('../rs/desafio/totalDesafioCerto/'+vm.usuario.matricula).then(function(response){
                sessionStorage.setItem('certos', response.data);
                axios.get('../rs/desafio/totalDesafioErrado/'+vm.usuario.matricula).then(function(response2){
                    sessionStorage.setItem('errado', response2.data);
                });
            });
        },
        calculaProgresso : function(){
            const vm =this;
            if(parseInt(vm.usuario.level)<12){
                vm.progresso = (100/12*parseInt(vm.usuario.level)).toFixed(2)+'%';
            } else {
                vm.progresso = (100/12*parseInt(vm.usuario.level)).toFixed(0)+'%';
            }
        },
        logout: function(){
            axios.post('../rs/user/logout').then(function(response) {
                sessionStorage.clear();
                window.location.href = "../index.html";
            }).catch(function (error){
                demo.novaMensagem('Erro ao realizar logout! Verifique sua conexão com a internet!', 4);
                demo.showNotification();
            });
        },
        verificaCampos : function (){
			const vm = this;
			vm.mensagens = [];
			var isOk = 1;
			if(vm.dados.nomeUsuario == ''){
				vm.mensagens.push('Nome do usuário é obrigatório');
			}
			if(vm.dados.email == ''){
				vm.mensagens.push('Email é obrigatório');
			} 
			if(vm.dados.desativado == ''){
				vm.mensagens.push('É obrigatório indicar o desativamento ou ativamento do perfil!');
			}
			
			for(var a = 0; a < vm.mensagens.length; a++){
				if(vm.mensagens[a] != null){
					demo.novaMensagem(vm.mensagens[a], 4);
					demo.showNotification();
				}
			}

			if(parseInt(vm.mensagens.length) > 0){
				isOk = 0;
			}
			return isOk;
        },
        salvaSenha : function(){
            const vm = this;
            $("#aguarde").modal("show");
            vm.defineSenha.matricula = vm.usuario.matricula;
            if(vm.defineSenha.novaSenha === vm.defineSenha.senhaTmp){
                axios.post('../rs/user/novaSenha', vm.defineSenha).then(function(response){
                    alert(response.data);
                }).finally(function(){
                    $("#aguarde").modal("hide");
                });
            } else {
                alert("A nova senha e a senha de confirmação devem ser iguais!");
            }
        },
        salvar(){
            const vm = this;
            if(this.verificaCampos() == 1){
                axios.post('../rs/user/atualizaDados', vm.dados).then(function(response){
                    if(vm.dados.desativado == 'S'){
                        $("#desativa").modal("show");
                    } else {
                        $("#sucessoAtualizaPerfil").modal("show");
                    }
                }).finally(function(){
                    vm.getUser();
                });
            }
        }
    },
    mounted : function(){
        const vm = this;

        if(sessionStorage.getItem('modalReativacaoPerfil') == 'S'){
            sessionStorage.removeItem('modalReativacaoPerfil');
            $("#reativado").modal("show");
        } else {
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


// let a = new Vue({
//     el : '#personagem',
//     data : {
//         idPersonagem : '',
//         nomePersonagem : '',
//         imgPersonagem : ''
//     },
//     mounted : function(){
//             const vm = this;
//             vm.idPersonagem = sessionStorage.getItem('idPersonagem');
//             vm.imgPersonagem = sessionStorage.getItem('imgPersonagem');
//             // Apagar
//                 // sessionStorage.setItem('nomePersonagem','Aisha');
//                 // sessionStorage.setItem('imgPersonagem', sessionStorage.getItem('imgPersonagem'));
//             if(vm.idPersonagem != null){
//                 axios.get('../rs/personagem/getPersonagem/'+vm.idPersonagem).then(function(response) {
//                     vm.nomePersonagem = response.data["nomePersonagem"];
//                     vm.imgPersonagem = response.data["imgPersonagem"];
//                     sessionStorage.setItem('nomePersonagem', vm.nomePersonagem);
//                 });
//             }
//     }
//  });

 function grafico (){
    var ctx = document.getElementById('myChart');  
    var myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Incorretos', 'Corretos'],
            datasets: [{
                label: ['Incorretos', 'Corretos'],
                data: [sessionStorage.getItem('errado'), sessionStorage.getItem('certos')],
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

grafico();