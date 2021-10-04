import React from 'react';
import AppBar from '@ui-lib/core/AppBar';
import Button from '@ui-lib/core/Button';
import Card from '@ui-lib/core/Card';
import CardActions from '@ui-lib/core/CardActions';
import CardContent from '@ui-lib/core/CardContent';
import CardHeader from '@ui-lib/core/CardHeader';
import Grid from '@ui-lib/core/Grid';
//import StarIcon from '@mui/icons-material/StarBorder';
import Toolbar from '@ui-lib/core/Toolbar';
import Typography from '@ui-lib/core/Typography';
import Link from '@ui-lib/core/Link';

const tiers = [
    {
        title: 'Free',
        price: '0',
        description: ['10 users included', '2 GB of storage', 'Help center access', 'Email support'],
        buttonText: 'Sign up for free',
        buttonVariant: 'outlined'
    },
    {
        title: 'Pro',
        subheader: 'Most popular',
        price: '15',
        description: ['20 users included', '10 GB of storage', 'Help center access', 'Priority email support'],
        buttonText: 'Get started',
        buttonVariant: 'contained'
    },
    {
        title: 'Enterprise',
        price: '30',
        description: ['50 users included', '30 GB of storage', 'Help center access', 'Phone & email support'],
        buttonText: 'Contact us',
        buttonVariant: 'outlined'
    }
];

const footers = [
    {
        title: 'Company',
        description: ['Team', 'History', 'Contact us', 'Locations']
    },
    {
        title: 'Features',
        description: ['Cool stuff', 'Random feature', 'Team feature', 'Developer stuff', 'Another one']
    },
    {
        title: 'Resources',
        description: ['Resource', 'Resource name', 'Another resource', 'Final resource']
    },
    {
        title: 'Legal',
        description: ['Privacy policy', 'Terms of use']
    }
];

function PricingContent() {
    return (
        <React.Fragment>
            <AppBar position="static" color="default" elevation={0} sx={{ borderBottom: (theme) => `1px solid ${theme.palette.divider}` }}>
                <Toolbar sx={{ flexWrap: 'wrap' }}>
                    <Typography variant="h6" color="inherit" noWrap sx={{ flexGrow: 1 }}>
                        Company name
                    </Typography>
                    <nav>
                        <Link variant="button" color="text.primary" href="#" sx={{ my: 1, mx: 1.5 }}>
                            Features
                        </Link>
                        <Link variant="button" color="text.primary" href="#" sx={{ my: 1, mx: 1.5 }}>
                            Enterprise
                        </Link>
                        <Link variant="button" color="text.primary" href="#" sx={{ my: 1, mx: 1.5 }}>
                            Support
                        </Link>
                    </nav>
                    <Button href="#" variant="outlined" sx={{ my: 1, mx: 1.5 }}>
                        Login
                    </Button>
                </Toolbar>
            </AppBar>
            {/* Hero unit */}
            <div>
                <Typography component="h1" variant="h2" align="center" color="text.primary" gutterBottom>
                    Pricing
                </Typography>
                <Typography variant="h5" align="center" color="text.secondary" component="p">
                    Quickly build an effective pricing table for your potential customers with this layout. It&apos;s built with default MUI components with little customization.
                </Typography>
            </div>
            {/* End hero unit */}

            <div>
                <Grid container spacing={5} alignItems="flex-end">
                    {tiers.map((tier) => (
                        // Enterprise card is full width at sm breakpoint
                        <Grid item key={tier.title} xs={12} sm={tier.title === 'Enterprise' ? 12 : 6} md={4}>
                            <Card>
                                <CardHeader
                                    title={tier.title}
                                    subheader={tier.subheader}
                                    titleTypographyProps={{ align: 'center' }}
                                    action={tier.title === 'Pro' ? /*<StarIcon />*/ null : null}
                                    subheaderTypographyProps={{
                                        align: 'center'
                                    }}
                                    sx={{
                                        backgroundColor: (theme) => (theme.palette.mode === 'light' ? theme.palette.grey[200] : theme.palette.grey[700])
                                    }}
                                />
                                <CardContent>
                                    <Typography component="h2" variant="h3" color="text.primary">
                                        ${tier.price}
                                    </Typography>
                                    <Typography variant="h6" color="text.secondary">
                                        /mo
                                    </Typography>
                                    <ul>
                                        {tier.description.map((line) => (
                                            <Typography component="li" variant="subtitle1" align="center" key={line}>
                                                {line}
                                            </Typography>
                                        ))}
                                    </ul>
                                </CardContent>
                                <CardActions>
                                    <Button fullWidth variant={tier.buttonVariant}>
                                        {tier.buttonText}
                                    </Button>
                                </CardActions>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            </div>
            {/* Footer */}
        </React.Fragment>
    );
}

export default function Pricing() {
    return <PricingContent />;
}
