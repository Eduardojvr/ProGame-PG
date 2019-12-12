new Vue({
    el:'#notifica',
    vuetify: new Vuetify(),
  data () {
    return {
      perPage: 5,
      currentPage: 1,
      search: '',
      headers: [
            {
              align: 'left',
              sortable: false,
            },
            { key: 'idDesafio', label: 'Prioridade Desafio' },
            { key: 'tituloDesafio', label: 'Título' },
            { key: 'desafio', label: 'Desafio' },
            { key: 'respostaDesafiado', label: 'Sua resposta' },
            { key: 'resultado', label: 'Resultado' } 
       ],
      desafios:[
          {
              desafio: '',
              idDesafio: '',
              matriculaDesafiado: '',
              matriculaDesafiante: '',
              respostaDesafiado: '',
              resultado: '',
              tituloDesafio:''
          }
      ],
      totalNotificacao: 0

    }
  },
  computed: {
    rows() {
      return this.desafios.length
    }
  },
    created: function(){
        const vm = this;
        var count = 0;
        axios.get('../rs/user/getUser').then(function(response){
            axios.get('../rs/user/todosDesafios/'+response.data["matricula"]).then(function(response){
                while(response.data[count]){
                    if(response.data[count]['resultado'] == '' || response.data[count]['resultado'] == null){
                        response.data[count]['resultado'] = 'Aguardando correção';
                    }

                    if(response.data[count]['respostaDesafiado'] == '' || response.data[count]['respostaDesafiado'] == null){
                        response.data[count]['respostaDesafiado'] = 'Sem resposta';
                    }
                    response.data[count]["idDesafio"] = count+1;
                    // vm.desafios.push(response.data[count]);
                    count+=1;
                }
                vm.totalNotificacao = count;
                vm.desafios = response.data;
            });
        });
    },
    methods: {
        responder: function(){
            alert();
        }
    }
});

