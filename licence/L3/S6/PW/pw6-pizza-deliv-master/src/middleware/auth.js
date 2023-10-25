function isAdmin(req, res, next) {
    if(req.session.user && req.session.user.user_role === "admin")
        next()
    else
        res.status(403).send({message: 'Access denied.'});
}

function isDelivery(req, res, next) {
    if(req.session.user && req.session.user.user_role === "delivery")
        next()
    else
        return isAdmin(req, res, next)
}

module.exports = {
    isAdmin,
    isDelivery
}