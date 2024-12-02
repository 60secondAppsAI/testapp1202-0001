<template> 





</template>
  
<script>

export default {
    components: {
    },
    props: {
        itemHandlings: Array,
    },

    methods: {

    },
};
</script>
  
<style scoped>
.card-container {
    display: flex;
    column-gap: 15px;
    flex-wrap: wrap;
    margin: 5px;

}

.card {
    max-width: 230px;

}

.itemHandling-picture-view {
    margin-bottom: 15px;
    /* Separate each row from the content below */
}

.itemHandling-card {
    max-width: 250px;
    min-width: 250px;
    border-radius: 30px;
    padding: 0px;
    margin-bottom: 25px;
    /* Separate each card */
}

.itemHandling-image {
				    	width: 250px;
	    	height: 200px;
	    	        	border-radius: 4px;
    }

.itemHandling-info {
    margin-top: 0px;
}

.itemHandling-name {
    font-weight: bold;
    font-size: 16px;
    margin-bottom: 2px;
    line-height: 1.2;
}

.itemHandling-details p {
    margin-top: 0px;
    text-align: left;
}

.itemHandling-link {
    display: inline;
    margin-top: 5px;
    color: #1890ff;
}
</style>
  
