let login = new Vue({
    el : '#login',
    data : {
        matricula : '',
		senha : '' 
    },
    methods : {
        logar : function() {
            const vm = this;
            let obj = {
                matricula: vm.matricula,
                senha: vm.senha
             }
            
            axios.post('/progame/rs/user/login', obj).then(function(response) {
                console.log(response.status);

                if(response.status == 200){
                    sessionStorage.setItem('nomeUsuario', response.data["nomeUsuario"]);
                    sessionStorage.setItem('matricula', response.data["matricula"]);
                    sessionStorage.setItem('idTipoPerfil', response.data["idTipoPerfil"]);
                    sessionStorage.setItem('idPersonagem', response.data["idPersonagem"]);
                    sessionStorage.setItem('email', response.data["email"]);

                    // gambiarra :(
                    sessionStorage.setItem('imgPersonagem', '../resources/img/persona.png');
                    if(sessionStorage.getItem('idPersonagem') != 2) {
                        sessionStorage.setItem('login', 1);
                    }
                    window.location.href = "pages/perfil.html"; 
                } else {
                    alert("Não foi possível realizar o login. Usuário ou senha incorretos!");
                }
               
            }).catch(function (error){
                sessionStorage.setItem('isLogado', false);
                alert("Erro ao realizar login");
            });
        },
        logout: function(){
            axios.post('/progame/rs/user/logout').then(function(response) {
                console.log(response);
                alert("Logout realizado com sucesso!");
                sessionStorage.clear();
                window.location.href = "../index.html";
            }).catch(function (error){
                alert("Erro ao realizar logout!");
            });
        }
    }
});
