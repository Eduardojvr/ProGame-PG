let usuario = new Vue({
	el : '#cadastro',
    data : {
		nomeUsuario : '',
        matricula : '',
		senha : '',
		idTipoPerfil : '',
		idPersonagem : '',
		email : ''
        
    },
	methods : {
		cadastra : function() {
	    	const vm = this;
            let obj = {
                nomeUsuario : vm.nomeUsuario,
                matricula : vm.matricula,
                senha : vm.senha,
                idTipoPerfil : '3',
                idPersonagem : null,
                email : vm.email
            }
			axios.post('rs/user/insert', obj).then(function(response) {
				if(response.status == 200){
					alert("Usuário cadastrado com sucesso!");
					window.location.href = 'login.html';
				} 
			}).catch(function (error){
                alert("Erro, não foi possível cadastrar usuário!");
            });
		}
	}
});
