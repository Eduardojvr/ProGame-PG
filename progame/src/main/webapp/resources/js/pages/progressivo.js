
let progress = new Vue({
    el: '#levelJogador',
    data: {
        level: ''
    },
    created : function(){
        const vm = this;
        vm.getUser();
        
    },
    methods : {
        getUser : function(){
            const vm = this;
            axios.get('../rs/user/getUser').then(function(response) {
                vm.level = response.data["level"];
                if((vm.level) >=1 && (vm.level) <= 9){
                    vm.level = '0'+vm.level;
                } 
            });
          
        }
    }
});
