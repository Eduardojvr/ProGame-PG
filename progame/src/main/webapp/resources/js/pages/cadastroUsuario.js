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
			axios.post('/progame/rs/user/insert', obj).then(function(response) {
				console.log(response);
			});
		}
	}
});
