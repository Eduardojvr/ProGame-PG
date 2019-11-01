let linguagem = new Vue({
	el : '#linguagem',
    data : {
		nomeLinguagem : '',
		descLinguagem : ''
    },
	methods : {
		cadastra : function() {
	    	const vm = this;
            let obj = {
                nomeLinguagem : vm.nomeLinguagem,
			    descLinguagem : vm.descLinguagem
            }
			axios.post('/progame/rs/linguagem/inserir', obj).then(function(response) {
				console.log(response);
			});
		}
	}
});
