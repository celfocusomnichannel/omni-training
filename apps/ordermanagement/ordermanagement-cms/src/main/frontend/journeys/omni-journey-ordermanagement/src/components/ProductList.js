import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { withStyles } from '@ui-lib/core/styles';
import Card from '@ui-lib/core/Card';
import CardActionArea from '@ui-lib/core/CardActionArea';
import CardActions from '@ui-lib/core/CardActions';
import CardContent from '@ui-lib/core/CardContent';
import CardMedia from '@ui-lib/core/CardMedia';
import Button from '@ui-lib/core/Button';
import Typography from '@ui-lib/core/Typography';
import Icon from '@ui-lib/core/Icon';
import DialogTitle from '@ui-lib/core/DialogTitle';
import Dialog from '@ui-lib/core/Dialog';
import List from '@ui-lib/core/List';
import ListItem from '@ui-lib/core/ListItem';
import ListItemText from '@ui-lib/core/ListItemText';
import { selectProducts } from '../redux/products/httpActions';

const styles = {
    media: {
        height: 140,
        width: 250
    },
    card: {
        maxWidth: 250,
        maxHeight: 250,
        marginLeft: '4vw'
    },
    cartButton: {
        textAlign: 'end',
        marginBottom: '2vh'
    },
    content: {
        height: '65vh',
        overflow: 'auto',
        display: 'flex'
    }
};

function ProductList({ classes }) {
    const listProducts = useSelector((state) => state.products.listProducts);
    const instance = useSelector((state) => state.products.instance);
    const [isDialogOpen, setDialogOpen] = React.useState(false);
    const dispatch = useDispatch();

    return (
        <div>
            <CartDialog open={isDialogOpen} onClose={() => setDialogOpen(false)} />
            <div className={classes.cartButton}>
                <Button variant="contained" color="primary" onClick={() => setDialogOpen(true)}>
                    Cart <Icon className={'icon-ecommerce'} /> ({instance.products.length})
                </Button>
            </div>
            <div className={classes.content}>
                {listProducts.map((e, i) => (
                    <Card key={e.productName + i} className={classes.card}>
                        <CardActionArea>
                            <CardMedia className={classes.media} image="https://cuoredimamma.pt/wp-content/uploads/2018/11/blog-ph.jpg" title="Product" />
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="h2">
                                    {e.productName}
                                </Typography>
                                <Typography component="p">{e.productPrice}€</Typography>
                            </CardContent>
                        </CardActionArea>
                        <CardActions>
                            <Button size="small" color="primary" onClick={() => dispatch(selectProducts(instance.jwcontext.id, e.productId))}>
                                Add to Cart
                            </Button>
                        </CardActions>
                    </Card>
                ))}
            </div>
        </div>
    );
}

export default withStyles(styles)(ProductList);

function CartDialog({ onClose, ...other }) {
    const instance = useSelector((state) => state.products.instance);

    return (
        <Dialog onClose={() => onClose()} aria-labelledby="simple-dialog-title" {...other}>
            <DialogTitle id="simple-dialog-title">Shopping Cart</DialogTitle>
            <div>
                <List>
                    {instance &&
                        instance.products &&
                        instance.products.map((product, index) => (
                            <ListItem key={product.productName + ' ' + index}>
                                <ListItemText primary={product.productName} />
                                <ListItemText primary={product.productPrice} />
                            </ListItem>
                        ))}
                </List>
            </div>
        </Dialog>
    );
}
