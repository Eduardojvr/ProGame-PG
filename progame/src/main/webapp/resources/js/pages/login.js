let login = new Vue({
    el : '#login',
    data : {
        matricula : '',
        senha : '' 
    },
    methods : {
        logar : function(modalName) {
            const vm = this;
            let obj = {
                matricula: vm.matricula,
                senha: vm.senha
             }

            abreLoad(modalName);
            axios.post('rs/user/login', obj).then(function(response) {
                if(response.status == 200){
                    sessionStorage.setItem('idPersonagem', response.data["idPersonagem"]);

                    // gambiarra :(
                    sessionStorage.setItem('imgPersonagem', '../resources/img/persona.png');
                    if(sessionStorage.getItem('idPersonagem') != 7) {
                        sessionStorage.setItem('login', 1);
                    }

                    // if(sessionStorage.getItem('idPersonagem') == 7 && response.data["idTipoPerfil"] == 3 ){
                    //     window.location.href = "pages/narrativa.html"; 
                    // } else
                     if(response.data["idTipoPerfil"] == 2){
                        window.location.href = "pages/administrador/adm.html"; 
                    }else if(response.data["idTipoPerfil"] == 3){
                        window.location.href = "pages/perfil.html"; 
                    }
                } else {
                    alert("Não foi possível realizar o login. Usuário ou senha incorretos!");
                }
                fechaLoad(modalName);
            
            }).catch(function (error){
                alert("Erro ao realizar login :(");
                fechaLoad(modalName);

            });
        },
        logout: function(){
            axios.post('../rs/user/logout').then(function(response) {
                sessionStorage.clear();
                window.location.href = "../index.html";
            }).catch(function (error){
                alert("Erro ao realizar logout!");
            });
        }
    }
});
